$('document').ready(function() {
    
    $('.table #petDetailsButton').on('click', function (event) {
        event.preventDefault();
        
        let href = $(this).attr('href');
        $.get(href, function (pet) {
           $('#petId').val(pet.id);
           $('#petName').val(pet.name);
           $('#petType').val(pet.petTypeDTO.name);

           if (pet.adopted == true) {
               $('#adopted').val('Yes - ' + formatUTC(pet.adoptionDate));
           } else {
               $('#adopted').val('No');
           }

           let details = pet.petDetailsDTO;
           $('#petGender').val(details.gender);
           $('#petSize').val(details.size);
           $('#petAge').val(details.age);
           $('#petDescription').val(details.description);
           $('#sterilized').val(details.sterilized == true ? 'Yes' : 'No');
           $("#petAddress").val(formatAddress(pet.addressDTO));

           if (pet.imageUrl === "")
                $('#petImage').attr('src','/img/imageNotFound.png');
           else
                $('#petImage').attr('src','/pets-images/' + pet.imageUrl);

           $('#createdDate').val(formatUTC(pet.createdDate));
           $('#lastModifiedDate').val(formatUTC(pet.lastModifiedDate));
        });

        $('#detailsPetModal').modal();
    });

    $('.table #petDeleteButton').on('click',function(event) {
        event.preventDefault();
        let href = $(this).attr('href');
        $('#deletePetModal #confirmDeleteButton').attr('href', href);
        $('#deletePetModal').modal();
    });
});

function toggleAdoption(element) {
    let parentDiv = element.parentNode;
    let form = parentDiv.parentNode;
    $.get(form.action, {adopted: element.checked});
}

function formatAddress(address) {
    let formatted = '';

    if (address.country.trim())
        formatted += 'Country: ' + address.country;

    if (address.state.trim())
        formatted += ', State: ' + address.state;

    if (address.city.trim())
        formatted += ', City: ' + address.city;

    if (address.zipCode.trim())
        formatted += ', Zip Code: ' + address.zipCode;

    if (address.streetAddress.trim())
        formatted += ', Street: ' + address.streetAddress;

    return formatted;
}

