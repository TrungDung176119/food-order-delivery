/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */


function formatPrice(price) {
    return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
}

document.addEventListener("DOMContentLoaded", function () {
    const priceElements = document.querySelectorAll(".order-product-old__price, .order-product-current__price, .order-total__price ");
    priceElements.forEach(function (element) {
        const price = parseInt(element.textContent.replace(".", ""));
        element.textContent = "₫" + formatPrice(price);
    });
});


// rating star
document.addEventListener("DOMContentLoaded", function () {
    const selectedRatingInput = document.getElementById("selectedRating");
    const ratingDescription = document.getElementById("ratingDescription");
    const stars = document.querySelectorAll(".star");

    setDefaultStars();

    stars.forEach((star) => {
        star.addEventListener("click", function () {
            const rating = this.getAttribute("data-rating");
            resetStars();
            highlightStars(rating);
            selectedRatingInput.value = rating;
            updateRatingDescription(rating);
        });
    });

    function setDefaultStars() {
        resetStars();
        highlightStars(5); // Hiển thị tất cả các sao
        selectedRatingInput.value = stars.length;
        updateRatingDescription(5);
    }
    function resetStars() {
        stars.forEach((star) => {
            star.classList.remove("active");
        });
    }

    function highlightStars(rating) {
        for (let i = 0; i < rating; i++) {
            stars[i].classList.add("active");
        }
    }

    function updateRatingDescription(rating) {
        const descriptions = ["Tệ", "Không hài lòng", "Bình thường", "Hài lòng", "Tuyệt vời"];
        ratingDescription.textContent = descriptions[rating - 1];
    }

    // Optional: Set initial rating from server (replace with actual value)
    const initialRatingFromServer = "${initialRatingFromServer}";
    if (initialRatingFromServer) {
        highlightStars(initialRatingFromServer);
        updateRatingDescription(initialRatingFromServer);
    }
});

function submitRating() {
    const ratingForm = document.getElementById("ratingForm");
    ratingForm.submit();
}

document.addEventListener("DOMContentLoaded", function () {

    function formatPrice(price) {
        return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
    }
    const priceElements = document.querySelectorAll(".order-product-old__price, .order-product-current__price, .order-total__price ");
    priceElements.forEach(function (element) {
        const price = parseInt(element.textContent.replace("₫", "").replace(/\./g, ""));
        element.textContent = "₫" + formatPrice(price);
    });
});