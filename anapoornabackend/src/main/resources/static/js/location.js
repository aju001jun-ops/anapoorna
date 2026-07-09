// Save Location
let allLocations = {};
let allMarkers = {};
document.getElementById("saveLocation").addEventListener("click", function () {

    const imageFile = document.getElementById("image").files[0];

    // No image selected
    if (!imageFile) {
        alert("Please select an image.");
        return;
    }

    const formData = new FormData();
    formData.append("image", imageFile);

    // Step 1: Upload Image
    fetch("/api/upload", {

        method: "POST",
        body: formData

    })

    .then(response => response.text())

    .then(imagePath => {

        // Step 2: Save Location

        const locationData = {

            personName: document.getElementById("personName").value,

            personType: document.getElementById("personType").value,

            description: document.getElementById("description").value,

            phone: document.getElementById("phone").value,

            address: document.getElementById("address").value,

            postedBy: document.getElementById("postedBy").value,

            latitude: parseFloat(document.getElementById("latitude").value),

            longitude: parseFloat(document.getElementById("longitude").value),

            imagePath: imagePath,

            active: true

        };

        return fetch("/api/locations/save", {

            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify(locationData)

        });

    })

    .then(response => response.json())

    .then(data => {

        alert("✅ Location Saved Successfully!");

        loadLocations();

        bootstrap.Modal.getInstance(
            document.getElementById("locationModal")
        ).hide();

        document.getElementById("locationForm").reset();

    })

    .catch(error => {

        console.error(error);

        alert("Error saving location.");

    });

});
// ================================
// Load All Saved Locations
// ================================

function loadNearbyLocations() {
	console.log("loadNearbyLocations called");
	const keyword = document
	    .getElementById("searchNearby")
	    .value
	    .toLowerCase();

	const selectedType = document.getElementById("typeFilter").value;

	const maxDistance = parseFloat(
	    document.getElementById("distanceFilter").value
	);

    const nearbyList = document.getElementById("nearbyList");

    nearbyList.innerHTML = "";

    fetch("/api/locations")

        .then(response => response.json())

        .then(data => {

            data.forEach(location => {

                

				const typeMatches =
				    selectedType === "All" ||
				    location.personType === selectedType;

					let distance = 0;
					let distanceMatches = true;

					// Only calculate distance if user location exists
					if (userLatitude != null && userLongitude != null) {

					    distance = calculateDistance(
					        userLatitude,
					        userLongitude,
					        location.latitude,
					        location.longitude
					    );

					    distanceMatches = distance <= maxDistance;
					}
					const searchMatches =
					    location.personName.toLowerCase().includes(keyword) ||
					    location.address.toLowerCase().includes(keyword);

				if(typeMatches && distanceMatches && searchMatches){

					nearbyList.innerHTML += `

					<div class="location-card"
					     id="card-${location.id}"
					     onclick="focusLocation(${location.id})">
					    <img src="${location.imagePath || '/images/no-image.png'}"
					         style="width:100%;height:140px;object-fit:cover;">

					    <div class="location-body">

					        <h5>${location.personName}</h5>

					        <p>${location.address}</p>

							<p>

							${userLatitude != null
							    ? distance.toFixed(2) + " km away"
							    : "Distance unavailable"}

							</p>
					    </div>

					</div>

					`;

                }

            });

        });

}
const nearbyList = document.getElementById("nearbyList");

console.log(nearbyList);
function focusLocation(id){

    // Remove previous selection
    document
        .querySelectorAll(".location-card")
        .forEach(card => card.classList.remove("selected-card"));

    // Highlight selected card
    const card = document.getElementById("card-" + id);

    if(card){

        card.classList.add("selected-card");

        card.scrollIntoView({
            behavior:"smooth",
            block:"center"
        });

    }

    // Get location from memory
    const location = allLocations[id];

    if(!location) return;

    // Move map
    map.setView([location.latitude, location.longitude],18);

    // Open the existing marker popup
    if(allMarkers[id]){

        allMarkers[id].openPopup();

    }

}
function loadLocations() {

    markerLayer.clearLayers();

    allMarkers = {};

    fetch("/api/locations")

        .then(response => response.json())

        .then(data => {

            data.forEach(location => {

                allLocations[location.id] = location;

                const marker = L.marker([location.latitude, location.longitude])

                    .addTo(markerLayer)

                    .bindPopup(`

                    <div style="width:260px">

                        <img src="${location.imagePath}"
                             style="width:100%;
                                    height:180px;
                                    object-fit:cover;
                                    border-radius:10px;">

                        <h5 class="mt-2">${location.personName}</h5>

                        <hr>

                        <p><b>👤 Type:</b> ${location.personType}</p>

                        <p>📍 ${location.address}</p>

                        <p>📝 ${location.description}</p>

                        <p>📞 ${location.phone}</p>

                        <p>👨‍💼 Posted by ${location.postedBy}</p>

                        <button class="btn btn-success w-100">
                            🍱 Donate Food
                        </button>

                    </div>

                    `);

                allMarkers[location.id] = marker;

            });

        });

}
function calculateDistance(lat1, lon1, lat2, lon2) {

    const R = 6371; // Earth's radius in km

    const dLat = (lat2 - lat1) * Math.PI / 180;
    const dLon = (lon2 - lon1) * Math.PI / 180;

    const a =
        Math.sin(dLat / 2) * Math.sin(dLat / 2) +
        Math.cos(lat1 * Math.PI / 180) *
        Math.cos(lat2 * Math.PI / 180) *
        Math.sin(dLon / 2) *
        Math.sin(dLon / 2);

    const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

    return R * c;
}
document.getElementById("searchNearby")
.addEventListener("keyup", loadNearbyLocations);
document.getElementById("typeFilter")
.addEventListener("change", loadNearbyLocations);

document.getElementById("distanceFilter")
.addEventListener("change", loadNearbyLocations);


function loadSelectedLocation(id){

    console.log("Loading location:", id);

    // Open the right panel
    document.getElementById("panelContainer").classList.remove("d-none");
    document.getElementById("nearbyPanel").style.display = "flex";

    // Load all nearby cards
    loadNearbyLocations();

    // Wait for cards and markers to be ready
    setTimeout(() => {

        // Highlight card + move map + open popup
        focusLocation(id);

    }, 500);

}
// Load markers when page opens
loadLocations();
