package org.shop.config;

import org.shop.api.ItemService;
import org.shop.api.OrderService;
import org.shop.api.ProductService;
import org.shop.api.ProposalService;
import org.shop.api.SellerService;
import org.shop.api.UserService;
import org.shop.api.impl.ItemServiceImpl;
import org.shop.api.impl.OrderServiceImpl;
import org.shop.api.impl.ProductServiceImpl;
import org.shop.api.impl.ProposalServiceImpl;
import org.shop.api.impl.SellerServiceImpl;
import org.shop.api.impl.UserServiceImpl;
import org.shop.repository.ItemRepository;
import org.shop.repository.ProductRepository;
import org.shop.repository.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShopServiceConfig {
    
    @Autowired
    private ItemRepository itemRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private ProposalRepository proposalRepository;
    
    @Bean
    public ItemService itemService() {
        return new ItemServiceImpl(itemRepository);
    }
    
    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl();
    }
    
    @Bean
    public ProductService productService() {
        return new ProductServiceImpl(productRepository);
    }
    
    @Bean
    public SellerService sellerService() {
        return new SellerServiceImpl();
    }
    
    @Bean(name = {"userService", "userManager"})
    public UserService userService() {
        return new UserServiceImpl();
    }
    
    @Bean
    public ProposalService proposalService() {
        return new ProposalServiceImpl(proposalRepository, sellerService(), productService());
    }
}
