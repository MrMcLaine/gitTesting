var magazines = null;

$.get("home", function(data) {
    if (data !== '') {
        magazines = data;
    }
}).done(function() {

    var cardsContent = "";
    jQuery.each(magazines, function(i, value) {

        cardsContent+="<div class='col'>" +
            "<div class='card'>" +
            "<div class='card-body'>" +
            "<h5 class='card-title'>" + value.name + "</h5>"+
            "<h6 class='card-subtitle mb-2 text-muted'>" + value.priceForMount + "</h6>"+
            "<p class='card-text'>" + value.description + "</p>"+
            "<a href='magazine?id=" + value.id + "' class='card-link'>See more</a>"+
            "</div>" +
            "</div>" +
            "</div>" +
            "</div>"
    });

    $('#magazineCards').html(cardsContent);

});