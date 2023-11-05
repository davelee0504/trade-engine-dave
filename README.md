# Simple Trade Engine
Trade Engine Coding Challenge xigolorez

# Trade Engine Structure
- RESTful API to receive orders
- Sell/Buy Orders kept by PriorityBlockingQueue
- Matching Strategy
- Scheduled Tasks to process orders
- Tests focus on Trading Engine

# Order Matching Algorithm
Based on Price/Time priority.
The sell-side orders are sorted by price and timestamp in the ascending order.
The buy-side orders are sorted by price descending order and timestamp in the ascending order.

# RESTful Endpoints
- GET /order/{id}
- GET /order/all
- POST /order/buy
- POST /order/sell

You can use Postman by import the collection(Trade Engine.postman_collection.json)

Example of Order JSON
```json
{
    "price": 10.0,
    "quantity": 30,
    "type": "MARKET"
}

```

Command for test rate limiting
```shell
ab -n 100 -c 100 -T 'application/json' -p /Users/davelee/IdeaProjects/trade-engine-dave/Order.json http://localhost:8080/order/buy
```