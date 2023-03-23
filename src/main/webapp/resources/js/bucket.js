function myFunction() {
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("myInput");
    filter = input.value.toUpperCase();
    table = document.getElementById("myTable");
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[0];
        if (td) {
            txtValue = td.textContent || td.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}

var bucket = null;
$.get("bucket", function (data) {
    if (data !== '') {
        bucket = data;
    }
}).done(function () {

    var tableContent = "<tr class='header'>" +
        "<th style='width: 20%;'>Name of the magazine</th>" +
        "<th style='width: 20%;'>Description</th>" +
        "<th style='width: 20%;'>Date Of Payment</th>" +
        "<th style='width: 20%;'>Sum of payment</th>" +
        "<th style='width: 20%;'>Options</th>" +
        "</tr>";

    jQuery.each(bucket, function (i, value) {

        tableContent += "<tr>" +
            "<td>" + value.magazineName + "</td>" +
            "<td>" + value.magazineDescription + "</td>" +
            "<td>" + value.dateOfPayment + "</td>" +
            "<td>" + value.sumPayment + "</td>" +
            "<td><button onclick='deleteOrderFromBucket(" + value.paymentId + ")'>delete</button></td>" +
            "</tr>"

    });

    $('#myTable').html(tableContent);

});

function deleteOrderFromBucket(paymentId) {
    var customUrl = '';
    var urlContent = window.location.href.split('/');
    for (var i = 0; i < urlContent.length - 1; i++) {
        customUrl += urlContent[i] + '/'
    }
    customUrl += 'payment?paymentId=' + paymentId;

    $.ajax({
        url: customUrl,
        type: 'DELETE',
        success: function (data) {
            if (data == 'Success') {
                location.reload();
            }
        }
    });
}
