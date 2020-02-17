package ch.thegli.spring.aop.web;

import ch.thegli.spring.aop.logging.AllPublicMethodsTraced;
import ch.thegli.spring.aop.logging.PublicMethodDuration;
import ch.thegli.spring.aop.logging.PublicMethodDurationLogger;
import ch.thegli.spring.aop.model.Product;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.logging.Logger;

@RestController
@RequestMapping("/acme")
@AllPublicMethodsTraced
public class AcmeController {
    private static final Logger LOG = Logger.getLogger(AcmeController.class.getName());

    @RequestMapping(value = "/{productId}", method = RequestMethod.GET, produces = "application/json")
    public Callable<Product> product(@PathVariable("productId") final int id) {
        Product product = new Product("bargain-" + id);
        LOG.info("Returning product with id " + id);
        return () -> product;
    }

    @PublicMethodDuration
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public Callable<List<Product>> products() {
        List products = new ArrayList<>();
        products.add(new Product("things"));
        products.add(new Product("stuff"));
        products.add(new Product("more things"));
        LOG.info("Returning all products");
        return () -> products;
    }
}
