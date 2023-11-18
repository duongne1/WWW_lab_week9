package fit.iuh.edu.vn.week7.ids;

import fit.iuh.edu.vn.week7.models.Order;
import fit.iuh.edu.vn.week7.models.Product;

import java.io.Serializable;

public class OrderDetailPK implements Serializable {
    private Order order;
    private Product product;
}
