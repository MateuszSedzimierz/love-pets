$('document').ready(function() {
    
    $('.table #addressDetailsButton').on('click', function (event) {
        event.preventDefault();
        
        let href = $(this).attr('href');
        $.get(href, function (address) {
           $('#addressCountry').append(address.country);

           if (address.state) {
               $('#addressState').append("<b>State:</b><p>" + address.state + "</p>");
           }

           $('#addressCity').append(address.city);

           if (address.zipCode) {
               $('#addressZipCode').append("<b>Zip Code:</b><p>" + address.zipCode + "</p>");
           }

           if (address.streetAddress) {
               $('#addressStreet').append("<b>Street Address:</b><p>" + address.streetAddress + "</p>");
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
