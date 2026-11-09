package tn.esprit.autoloc.service;

public interface IGenericService<T, ID>
        extends IReadGenericService<T, ID>, ICheckGenericService<T>, IWriteGenericService<T, ID> {
}
