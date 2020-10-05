# Answer to the refactoring question

## 1) Do a code review of this method. What would be your feedback to the author? Write down your review.

#### LinkedList transformation not good way
About the method return type and the transformation did at the end of the method `return new LinkedList(l);`:  
it's not good to create systematically another object from the generated one inside the method.  
You can use another method for this task or do it in the code whereas you need it.

#### Boolean Debug to remove
About the method parameters: "boolean debug" is not used, so it should be removed.

#### ArrayList elements declaration and useless casting of (order)
About the method declaration and all the places in which an ArrayList supposed to contain elements of Order are declared:  
you should add the kind of objects the arraylist is supposed to contain, this way: `ArrayList<Order>`.
Also, by doing this way, the casting `(Order)` was no longer necessary.

#### Error on the first iteration
About the line `Order order = (Order) list.get(0);`: functional error, it should be `list.get(i)`!!!  

#### Replace the first iteration with an enhanced for.
Once done all the above changes, you can now replace the conventional for loop with a new enhanced for, replacing this
```java
for (int i = 0; i < list.size(); i++) {
            Order order = list.get(i);
``` 
with this
```java
for (Order order : list) {
```

#### Useless `ArrayList<Order> list` local variable definition
There is no need to declare the list of order because it’s used only once, so it can be inlined replacing this
```java
ArrayList<Order> list = getAllOrders();
        for (Order order : list) {
``` 
with this
```java
for (Order order : getAllOrders()) {
```
        
#### Error on the second iteration
About the line `for (int j = 0; j <= order.getProducts().size(); j++)`, there was another critical error here producing a potential `IndexOutOfBoundException`:  
the for should be repeated only until `j` is just less than (`<`) `order.getProducts().size()`, not less than and equal (`<=`).

#### Useless boolean local variable definition
about the line `boolean found = false;`: no need to declare a local variable `found`, we can just check it during the cycle, as now is done by `if (p2 == p)`.

#### Useless null check
About the line `if (found && order != null)` : no need to check if it's not null.

#### Useless Product local variable declaration
now the local variable `p2` can be inlined because it's used only once, replacing this
```java
Product p2 = order.getProducts().get(j);
                    if (p2 == p){
``` 
with this
```java
order.getProducts().get(j) == p
```

#### Replace the second iteration with an enhanced for
Also the second for cycle can be converted to an enhanced for loop: `for (Product product : order.getProducts())`.

#### Avoid to add orders multiple times
Finally, once that the same product is found for a specific order, you can add the order and then skip the remaining
 products in the order by adding a `break` statement.

## 2) Refactor this method according to your feedback

```java
    public ArrayList<Order> findOrdersForProduct(Product p) {
        ArrayList<Order> l = new ArrayList<>();
        for (Order order : getAllOrders()) {
            if (order.getProducts().size() > 0) {
                for (Product product : order.getProducts()) {
                    if (product == p) {
                        l.add(order);
                        break;
                    }
                }
            }
        }
        return l;
    }
```

2020/10/03
Alessandro Iudicone