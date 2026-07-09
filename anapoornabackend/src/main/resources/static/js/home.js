document.getElementById("orphanageCount").innerHTML = 15;

document.getElementById("elderlyCount").innerHTML = 42;

document.getElementById("homelessCount").innerHTML = 11;

document.getElementById("postCount").innerHTML = 68;
loadRecentPosts();

function loadRecentPosts(){

    fetch("/api/locations")

    .then(response=>response.json())

    .then(data=>{

        data.sort((a,b)=>

            new Date(b.createdAt)-new Date(a.createdAt)

        );

        let html="";

        data.slice(0,6).forEach(location=>{

            let image=location.imagePath;

            if(image==null || image==""){

                image="/images/no-image.png";

            }

            html+=`

            <div class="col-md-4">

                <div class="recent-card">

                    <img src="${image}">

                    <div class="recent-body">

                        <h4 class="recent-title">

                            ${location.personName}

                        </h4>

                        <p class="recent-address">

                            📍 ${location.address}

                        </p>

                        <p>

                            👤 ${location.personType}

                        </p>

                        <button

                            class="btn btn-success recent-btn"

                            onclick="openMap(${location.id})">

                            View on Map

                        </button>

                    </div>

                </div>

            </div>

            `;

        });

        document.getElementById("recentPostContainer").innerHTML=html;

    });

}
function openMap(id){

    window.location.href="/map?id="+id;

}
loadCategory("Orphanage","orphanageContainer");
loadCategory("Elderly","elderlyContainer");
loadCategory("Homeless","homelessContainer");

function loadCategory(type, containerId){

    fetch("/api/locations")

    .then(response=>response.json())

    .then(data=>{

        let html="";

        let filtered=data.filter(

            item=>item.personType===type

        );

        filtered.slice(0,4).forEach(location=>{

            let image=location.imagePath;

            if(image==null || image==""){

                image="/images/no-image.png";

            }

            html+=`

            <div class="col-lg-3 col-md-6">

                <div class="category-card">

                    <img src="${image}">

                    <div class="category-body">

                        <h5 class="category-title">

                            ${location.personName}

                        </h5>

                        <p class="category-address">

                            📍 ${location.address}

                        </p>

                        <button
                        class="btn btn-success category-btn w-100"

                        onclick="openMap(${location.id})">

                        View on Map

                        </button>

                    </div>

                </div>

            </div>

            `;

        });

        document.getElementById(containerId).innerHTML=html;

    });

}