package pl.sedzimierz.lovepets.service.mapper;

public interface BaseMapper<E, D> {

    E mapToEntity(D DTO);

    D mapToDTO(E entity);
}
