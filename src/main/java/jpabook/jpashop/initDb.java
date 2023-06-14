package jpabook.jpashop;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class initDb {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() {
            Member member = createMember("userA", "서울", "1", "2");
            em.persist(member);

            Book book1 = new Book();
            createBook(book1, "JPA1 BOOK", 10000, 100);
            em.persist(book1);

            Book book2 = new Book();
            createBook(book2, "JPA2 BOOK", 20000, 200);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 10000, 2);

            Delivery delivery = new Delivery();
            Order order = createDelivery(member, orderItem1, orderItem2, delivery);
            em.persist(order);

        }

        public void dbInit2() {
            Member member = createMember("userB", "진주", "2", "1");
            em.persist(member);

            Book spring1 = new Book();
            createBook(spring1, "SPRING1 BOOK", 30000, 300);
            em.persist(spring1);

            Book spring2 = new Book();
            createBook(spring2, "SPRING2 BOOK", 40000, 400);
            em.persist(spring2);

            OrderItem orderItem1 = OrderItem.createOrderItem(spring1, 30000, 3);
            OrderItem orderItem2 = OrderItem.createOrderItem(spring2, 40000, 4);

            Delivery delivery = new Delivery();
            Order order = createDelivery(member, orderItem1, orderItem2, delivery);
            em.persist(order);

        }

        private static Order createDelivery(Member member, OrderItem orderItem1, OrderItem orderItem2, Delivery delivery) {
            delivery.setAddress(member.getAddress());
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            return order;
        }

        private static void createBook(Book book1, String JPA1_BOOK, int price, int stockQuantity) {
            book1.setName(JPA1_BOOK);
            book1.setPrice(price);
            book1.setStockQuantity(stockQuantity);
        }

        private static Member createMember(String name, String city, String street, String zipcode) {
            Member member = new Member();
            member.setName(name);
            member.setAddress(new Address(city, street, zipcode));
            return member;
        }
    }
}

