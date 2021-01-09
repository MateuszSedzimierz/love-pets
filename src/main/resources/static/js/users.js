$('document').ready(function() {
    
    $('.table #addressDetailsButton').on('click', function (event) {
        event.preventDefault();
        
        let href = $(this).attr('href');
        $.get(href, function (address) {
           $('#addressCountry').text(address.country);

           if (address.state) {
               $('#addressState').html("<b>State:</b><p>" + address.state + "</p>");
           } else {
               $('#addressState').empty();
           }

           $('#addressCity').text(address.city);

           if (address.zipCode) {
               $('#addressZipCode').html("<b>Zip Code:</b><p>" + address.zipCode + "</p>");
           } else {
               $('#addressZipCode').empty();
           }

           if (address.streetAddress) {
               $('#addressStreet').html("<b>Street Address:</b><p>" + address.streetAddress + "</p>");
           } else {
               $('#addressStreet').empty();
           }
        });

        $('#addressDetailsModal').modal();
    });

    $('.table #userEditButton').on('click', function (event) {
        event.preventDefault();

        let href = $(this).attr('href');
        $.get(href, function (user) {
            $('#userId').val(user.id);
            $('#userLogin').val(user.login);
            $('#userFirstName').val(user.firstName);
            $('#userLastName').val(user.lastName);
            $('#userEmail').val(user.email);
            $('#userPhoneNumber').val(user.phoneNumber);
            $('#addressId').val(user.addressDTO.id);
            $('#userCountry').val(user.addressDTO.country);
            $('#userState').val(user.addressDTO.state);
            $('#userCity').val(user.addressDTO.city);
            $('#userZipCode').val(user.addressDTO.zipCode);
            $('#userStreet').val(user.addressDTO.streetAddress);
        });

        $('#userEditModal').modal();
    });

    $('.table #userDeleteButton').on('click',function(event) {
        event.preventDefault();
        let href = $(this).attr('href');
        $('#deleteModal #confirmDeleteButton').attr('href', href);
        $('#deleteModal').modal();
    });
});

function toggleAdminSwitch(element) {
    let parentDiv = element.parentNode;
    let form = parentDiv.parentNode;
    $.get(form.action, {isAdmin: element.checked});
}
