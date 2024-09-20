package org.ritpl.repository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import org.ritpl.entity.Car;

import java.util.List;

@ApplicationScoped
public class CarRepository implements PanacheRepository<Car> {

    public List<Car> findCarsByMake(String company, int pageIndex, int pageSize, String sortField, String sortOrder) {
        String sortQuery = sortField + " " + sortOrder;
        return find("LOWER(company) LIKE LOWER(?1)", "%" + company + "%")
                .page(pageIndex, pageSize)
                .list();
    }

    public List<Car> getAllCarsWithPaginationSortingAndFiltering(int pageIndex, int pageSize, String sortField, String sortOrder, String carName, String companyName) {
        Sort.Direction direction = sortOrder.equalsIgnoreCase("asc") ? Sort.Direction.Ascending : Sort.Direction.Descending;

        // Build query for filtering
        PanacheQuery<Car> query;

        if (carName != null && !carName.isEmpty()) {
            query = find("LOWER(model) LIKE LOWER(?1)", Sort.by(sortField).direction(direction), "%" + carName + "%");
        } else if (companyName != null && !companyName.isEmpty()) {
            query = find("LOWER(make) LIKE LOWER(?1)", Sort.by(sortField).direction(direction), "%" + companyName + "%");
        } else {
            query = findAll(Sort.by(sortField).direction(direction)); // No filtering, only sorting
        }
        // Apply pagination
        return query.page(Page.of(pageIndex, pageSize)).list();
    }


}
