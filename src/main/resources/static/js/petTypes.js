$('document').ready(function () {

    $('.table #petTypeDetailsButton').on('click', function (event) {
       event.preventDefault();
       let href = $(this).attr('href');
       $.get(href, function (petTypeDTO) {
           $('#petTypeId').val(petTypeDTO.id);
           $('#petTypeName').val(petTypeDTO.name);
           $('#petTypeCreatedBy').val(petTypeDTO.createdBy);
           $('#petTypeCreatedDate').val(formatUTC(petTypeDTO.createdDate));
           $('#petTypeLastModifiedBy').val(petTypeDTO.lastModifiedBy);
           $('#petTypeLastModifiedDate').val(formatUTC(petTypeDTO.lastModifiedDate));
           $('#detailsPetTypeModal').modal();
       })
    });
});
