package ua.magazines.service;

import ua.magazines.entity.Magazine;

import java.util.Map;

public interface MagazineService extends AbstractCrudService<Magazine> {
    Map<Integer, Magazine> readAllMap();
}
