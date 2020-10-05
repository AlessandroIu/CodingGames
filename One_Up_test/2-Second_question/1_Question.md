# Exercise 2: Legacy refactoring

A co-worker wrote this (terrible) method 10 years ago.  
The method goes across all Orders and returns the ones that contain the Product `p` in parameter.
1. Do a code review of this method. What would be your feedback to the author? Write down
your review.
2. Refactor this method according to your feedback.

```java
    public LinkedList findOrdersForProduct(Product p, boolean debug) {
        ArrayList l = new ArrayList();
        ArrayList list = getAllOrders();
        for (int i = 0; i < list.size(); i++) {
            Order order = (Order) list.get(0);boolean found = false;
            if (order.getProducts().size() > 0) {
                for (int j = 0; j <= order.getProducts().size(); j++) {
                    Product p2 = order.getProducts().get(j);
                    found = (p2 == p);
                }
                if (found && order != null)
                    l.add(order);
            }
        }
        return new LinkedList(l);
    }
```
*Suggested effort: 20 minutes*