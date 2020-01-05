package mtds.microservices.cart.controller;

import mtds.microservices.cart.dao.CartDao;
import mtds.microservices.cart.model.Cart;
import mtds.microservices.cart.model.CartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;

@RestController
@CrossOrigin
public class CartController {

    @Autowired
    private CartDao cartDao;

    @RequestMapping(value = "/carts", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Cart>> listCarts() {
        return new ResponseEntity<>(cartDao.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/addCart", method = RequestMethod.POST)
    public ResponseEntity<?> saveCart(@RequestBody CartDTO cartDTO) {
        Cart newCart = new Cart();
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();

        newCart.setProductName(cartDTO.getProductName());
        newCart.setQuantity(cartDTO.getQuantity());
        newCart.setDate(date);
        newCart.setPrice(cartDTO.getPrice());
        return ResponseEntity.ok(cartDao.save(newCart));
    }

}