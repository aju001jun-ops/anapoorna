// ==========================
// Annapoorna Map
// ==========================

// Create Map
let userLatitude = null;
let userLongitude = null;
var map = L.map('map').setView([20.5937, 78.9629], 5);

// OpenStreetMap Layer
L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    maxZoom: 19,
    attribution: '&copy; OpenStreetMap Contributors'
}).addTo(map);

// Store user's current location marker
let currentMarker = null;

// Store temporary pin marker
let selectedMarker = null;
let markerLayer = L.layerGroup().addTo(map);

// Add Pin Mode
let addPinMode = false;

// -------------------------
// My Location
// -------------------------

document.getElementById("myLocationBtn").addEventListener("click", function () {

    if (!navigator.geolocation) {

        alert("Geolocation is not supported by your browser.");
        return;
    }

    navigator.geolocation.getCurrentPosition(

        function (position) {

            const lat = position.coords.latitude;
            const lng = position.coords.longitude;
			
			userLatitude = lat;
			userLongitude = lng;
            // Move map
            map.setView([lat, lng], 16);

            // Remove previous marker
            if (currentMarker != null) {
                map.removeLayer(currentMarker);
            }

            // Add marker
            currentMarker = L.marker([lat, lng]).addTo(map);

            currentMarker
                .bindPopup("<b>📍 You are Here</b>")
                .openPopup();

        },

        function () {

            alert("Unable to get your current location.");

        }

    );

});

// -------------------------
// Add Pin Button
// -------------------------

document.getElementById("addPinBtn").addEventListener("click", function () {

    addPinMode = true;

    alert("Click anywhere on the map to place a location.");

});

// -------------------------
// Click on Map
// -------------------------

map.on("click", function (e) {

	
    if (!addPinMode)
        return;

    addPinMode = false;

    // Remove old marker
    if (selectedMarker != null) {
        map.removeLayer(selectedMarker);
    }
	

    // Add new marker
    selectedMarker = L.marker(e.latlng).addTo(map);

    // Store latitude & longitude
    document.getElementById("latitude").value = e.latlng.lat;
    document.getElementById("longitude").value = e.latlng.lng;
	fetch(`https://nominatim.openstreetmap.org/reverse?format=jsonv2&lat=${e.latlng.lat}&lon=${e.latlng.lng}`)
	    .then(response => response.json())
	    .then(data => {
	        document.getElementById("address").value = data.display_name;
	    })
	    .catch(error => {
	        console.error(error);
	    });

    // Open Bootstrap Modal
    const modal = new bootstrap.Modal(
        document.getElementById("locationModal")
    );

    modal.show();

});
let panelOpened = false;

document.getElementById("nearbyBtn").addEventListener("click", function () {

    const mapContainer = document.getElementById("mapContainer");

    const panelContainer = document.getElementById("panelContainer");

    if(panelOpened){
		loadNearbyLocations();
        panelContainer.classList.add("d-none");

        mapContainer.classList.remove("col-lg-8");

        mapContainer.classList.add("col-lg-12");

        panelOpened = false;

    }else{

        panelContainer.classList.remove("d-none");

        mapContainer.classList.remove("col-lg-12");

        mapContainer.classList.add("col-lg-8");

        panelOpened = true;

    }

    setTimeout(function(){

        map.invalidateSize();

    },300);

});

// -------------------------
// Open selected location from Home page
// -------------------------
window.addEventListener("load", function () {

    const params = new URLSearchParams(window.location.search);

    const locationId = params.get("id");

    console.log("Location ID:", locationId);

    if (locationId) {

        loadSelectedLocation(locationId);

    }

});