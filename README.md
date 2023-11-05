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

```json
//Example of Order JSON
        
{
    "price": 10.0,
    "quantity": 30,
    "type": "MARKET"
}

```

