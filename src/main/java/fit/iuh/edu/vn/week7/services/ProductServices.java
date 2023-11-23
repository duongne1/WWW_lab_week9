package fit.iuh.edu.vn.week7.services;

import fit.iuh.edu.vn.week7.models.Product;
import fit.iuh.edu.vn.week7.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductServices {
    @Autowired
    private ProductRepository repository;
    public Page<Product> findPaginate(int pageNo, int pageSize, String sortBy, String sortDirection)
    {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection),sortBy);
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
        return repository.findAll(pageable);
    }
}