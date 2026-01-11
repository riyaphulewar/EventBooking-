# Event Management API – Postman Workflow

This document explains the **complete end-to-end flow** of the Event Management backend using **Postman**. You can directly upload this file to GitHub as `README.md`.

---

## Base URL

```
http://localhost:8080
```

---

## Step 1: Register Organizer User

**Endpoint**

```
POST /auth/register
```

**Request Body**

```json
{
  "name": "Alice Organizer",
  "email": "alice@event.com",
  "password": "Alice123",
  "role": "ROLE_ORGANIZER"
}
```

**Description**

* Registers a new organizer user.
* Organizer can create and manage events.

---

## Step 2: Create Event (With Price)

**Endpoint**

```
POST /events/create
```

**Request Body**

```json
{
  "userName": "alice@event.com",
  "title": "Music Concert",
  "description": "A live music concert",
  "location": "City Hall",
  "date": "2026-02-15T19:00:00",
  "availableTickets": 100,
  "price": 50.0
}
```

**Description**

* Organizer creates an event.
* Includes ticket price.

---

## Step 3: Create Event (Without Price)

**Endpoint**

```
POST /events/create
```

**Request Body**

```json
{
  "userName": "alice@event.com",
  "title": "Music Concert",
  "description": "A live music concert",
  "location": "City Hall",
  "date": "2026-02-15T19:00:00",
  "availableTickets": 100
}
```

**Description**

* Event creation without price (free event or default pricing).

---

## Step 4: Book Ticket by Customer

**Endpoint**

```
POST /bookings/book/{eventId}
```

**Request Body**

```json
{
  "userName": "riya@test.com",
  "ticketCount": 1
}
```

**Description**

* Customer books tickets for an event.
* `eventId` is the ID of the created event.
* Available tickets decrease after booking.

---

## Step 5: Update Event

**Endpoint**

```
PUT /events/update/1
```

**Request Body**

```json
{
  "userName": "alice@event.com",
  "title": "Music Concert - Updated",
  "description": "Updated description for the concert",
  "location": "Grand City Hall",
  "date": "2026-02-15T20:00:00",
  "availableTickets": 120,
  "price": 55.0
}
```

**Descrip
