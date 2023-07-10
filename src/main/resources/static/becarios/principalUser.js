
function cargarLineas() {
    var cardDeckScroll = document.querySelector('.card-deck-scroll');
    var scrollBtnLeft = document.querySelector('.scroll-btn-left');
    var scrollBtnRight = document.querySelector('.scroll-btn-right');

    scrollBtnLeft.addEventListener('click', () => {
        const scrollAmount = 300;
        cardDeckScroll.scrollBy({
            left: -scrollAmount,
            behavior: 'smooth'
        });
    });

    scrollBtnRight.addEventListener('click', () => {
        const scrollAmount = 300;
        cardDeckScroll.scrollBy({
            left: scrollAmount,
            behavior: 'smooth'
        });
    });

    cardDeckScroll.addEventListener('scroll', () => {
        const scrollAmount = 300;
        scrollBtnLeft.style.opacity = cardDeckScroll.scrollLeft === 0 ? 0 : 1;
        scrollBtnRight.style.opacity = cardDeckScroll.scrollLeft + cardDeckScroll.clientWidth >= cardDeckScroll.scrollWidth ? 0 : 1;
    });

    var tokenJWT = localStorage.getItem('token');

    $.ajax({
        url: ip+"/user/lineaInvestigacion",
        type: "GET",
        headers: {
            "Authorization": "Bearer " + tokenJWT
        },
        success: function (data) {
            var cardContainer = document.getElementById('cardContainer');

            data.forEach(function (item) {
                var card = document.createElement('div');
                card.classList.add('card');

                var cardBody = document.createElement('div');
                cardBody.classList.add('card-body');

                var cardTitle = document.createElement('h5');
                cardTitle.classList.add('card-title');
                cardTitle.textContent = item.nombre;

                var cardDescription = document.createElement('p');
                cardDescription.classList.add('card-text');
                cardDescription.textContent = item.descripcion;

                cardBody.appendChild(cardTitle);
                cardBody.appendChild(cardDescription);
                card.appendChild(cardBody);
                cardContainer.appendChild(card);
            });
        },
        error: function (error) {
            console.log(error);
        }
    });
}