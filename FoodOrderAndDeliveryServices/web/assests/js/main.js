/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

function formatPrice(price) {
    return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
}

document.addEventListener("DOMContentLoaded", function () {
    const priceElements = document.querySelectorAll(".table__product-price, .home-product-item__price-old, .home-product-item__price-current,\n\
                        .product-describe__price-old, .product-describe__price-current, .cart-product-total,\n\
                        .cart-product-price__old, .cart-product-price__current, ._naoh3, .header__cart-item-price,\n\
                        .checkouttotal, .checkout-total-money, .chart-box__price,\n\
                        .page__shop-product-current__price");
    priceElements.forEach(function (element) {
        if (element.textContent.trim() !== "") {
            const price = parseInt(element.textContent.replace(".", ""));
            element.textContent = "₫" + formatPrice(price);
        } else {
            // Nếu nội dung của phần tử là rỗng, hiển thị số 0
            element.textContent = "₫0";
        }
    });
});

function formatAmountSold(amountSold) {
    if (amountSold >= 1000) {
        const formattedAmount = (amountSold / 1000).toLocaleString('en-US', {minimumFractionDigits: 1, maximumFractionDigits: 1}).replace(".", ",");
        return formattedAmount + "k";
    } else {
        return amountSold.toString();
    }
}

document.addEventListener("DOMContentLoaded", function () {
    const amountSoldElements = document.querySelectorAll(".amount_sold, .product-describe__sold-number");
    amountSoldElements.forEach(function (element) {
        const amountSold = parseInt(element.textContent.replace("Đã bán ", ""));
        element.textContent = formatAmountSold(amountSold);
    });
});

function formatAmountSold(amountSold) {
    return amountSold.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

document.addEventListener("DOMContentLoaded", function () {
    const amountSoldElements = document.querySelectorAll(".chart-box__order, .format-money");
    amountSoldElements.forEach(function (element) {
        const amountSold = parseInt(element.textContent.replace("Đã bán ", ""));
        element.textContent = formatAmountSold(amountSold);
    });
});


            