$("button.createProduct")
    .click(
        function () {

            var name = $("form.createProduct input.productName").val();
            var description = $("form.createProduct input.productDescription").val();
            var price = $("form.createProduct input.productPrice").val();

            var magazine = {
                name: name,
                description: description,
                price: price
            };

            $.post("magazine", magazine,
                function (data) {
                    if (data == 'Success') {
                        alert('Success');
                    }
                });
        });

$("button.buy-magazine").click(function () {
    var magazineId = jQuery(this).attr("magazine-id");
    var price = jQuery(this).attr("magazine-price");

    $.post("payment", {'magazineId': magazineId, 'price': price},
        function (data) {
            if (data == 'Success') {
                $('#buyMagazineModal').hide();
                alert('Success');
            }
        });
});