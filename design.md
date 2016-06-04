### How frauders fraud
Initially I thought about the Ledger asking the countries for some LegalEntites and then making Transactions between them. However that was pretty nonsensical.

We'll instead somewhat mimic real world : each LegalEntity has a **scale** value (from 0 to 3) which reprents how rich it is. Each day, the entity will recieve an (semi-randomized) amount of money depending on its scale. If the entity is a **Taxpayer** and decides to fraud (through mechanisms not entirely clear as of yet), it will query the Compagnies it owns (including the ones owned indirectly) to try and establish a chain to another country.

The entire chain could be determined by the taxpayer, but that's complicated. He'll instead just pass the hot potato to a company it owns, which might or might not do the same depending on its size.

As money passes through a company, it could decide to flag it as suspicious. Compagnies go through their countries to transfer money, so passing around the info that a transaction is fraudulent should be easy.

Proposition : **tax rate**
Tax would no longer be an abstraction but an actual thing frauders would pay to their Country. This allows for three things :
1. flavour
2. chain of compagnies will target the country with the lowest tax rate they can attain
3. Countries can flag Taxpayers who pay too little given their scale for review


### Interface
```
+-------------------------------------------------+
|                                                 |
|                                   +---------+   |
|   +---------------------------+   | on/off  |   |
|   |                           |   +---------+   |
|   |                           |   +---------+   |
|   |                           |   |         |   |
|   |        screen             |   |  keys   |   |
|   |                           |   |  panel  |   |
|   |                           |   |         |   |
|   |                           |   |         |   |
|   |                           |   +---------+   |
|   +---------------------------+   +---------+   |
|                                   |intel ins|   |
|                                   +---------+   |
+-------------------------------------------------+
```
Idea is to mimic an old terminal

The screen displays a terminal, with a number of different applications. The application is determined from the keys on the right panel.
Keys would be :
| (right)  |   (left)   |
|:--------:|:----------:|
|HOME      | MAIL       |
|EMBASSIES | WORLD BANK |
|HELP      | CREDITS    |

HOME would just give you a world map, your status, the time and so on
MAIL is your proto-email

EMBASSIES allow you to request countries for info
WORLD BANK allows you to see info from the Ledger

HELP explains the game
CREDITS are credits

