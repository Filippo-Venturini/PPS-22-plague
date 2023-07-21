# Product backlog

Sprint 1: 21-30 June

||||||||||
|------|-------|------|------|------|------|------|------|------|
|Product Backlog Item |Sprint Task |Volunteer | Initial Estimate of Effort | 1 | 2 | 3 | 4 | 5 |
|I need a way to make the game's logic start and evolve|model the basic structure of a game engine|Venturini|3|2|2|2|0|0|
|I would have a basic infection mechanism|create an Infection Handler|Di Girolamo|7|5|2|0|0|0|
||create virus structure|Di Girolamo|4|3|2|2|2|0|
||model the basic internal infection logic|Di Girolamo|5|3|2|1|1|0|
||model the basic external infection logic|Di Girolamo|5|4|3|2|2|0|
|I need a way to represent regions and boundaries|model different kinds of regions|Venturini|6|5|4|1|1|0|
||Obtain directly reachable regions|Venturini|5|2|1|0|0|0|
||have a logic representation for routes and a manager|Venturini|3|1|1|1|1|0|
||Obtain port/airport-reachable regions|Venturini|5|5|3|2|0|0|
|I need a way to configure the game settings for regions and virus|define the configuration files structure|Malucelli|4|3|2|1|1|0|
||model a mechanism to read the configuration of the regions and virus file|Malucelli|7|7|6|3|2|1|
||convert the loaded regions and virus data in model entities|Malucelli|8|6|4|2|2|0|
|I would have the world map|create a game window with the world map|Di Girolamo, Venturini, Malucelli|5|3|3|3|0|0|
||detect click on different regions|Di Girolamo, Venturini, Malucelli|7|7|7|7|3|0|

Sprint 2: 1-7 July
||||||||||
|------|-------|------|------|------|------|------|------|------|
|Product Backlog Item |Sprint Task |Volunteer | Initial Estimate of Effort | 1 | 2 | 3 | 4 | 5 |
|I want a better infection logic|Improve internal infection logic|Di Girolamo|4|4|4|2|2|1|
||Improve external infection logic|Di Girolamo|5|5|5|3|3|3|
|I want to add the possibility to power-up the virus|define power-up structure|Di Girolamo|4|2|1|1|0|0|
||define different power-ups' type and behaviour|Di Girolamo|7|7|4|3|0|0|
||model a way to apply power-ups to the virus|Di Girolamo|6|4|2|1|0|0|
|I want to complete the configuration loading |define the configuration files structure for routes|Malucelli|3|3|0|0|0|0|
||convert the loaded routes in model entities|Malucelli|6|6|0|0|0|0|
||complete regions' configuration loading|Malucelli|8|8|4|0|0|0|
|I want a way to identify the regions in the map|define a strategy for the regions' identification|Malucelli|6|5|5|5|0|0|
||associate click on map to the corresponding region|Malucelli|4|4|4|4|4|0|
|I want to improve route handling|design a better route handler|Venturini|2|2|0|0|0|0|
|I want to have a way for interact with the regions|define the structure of the world|Venturini|6|3|3|1|0|0|
||I want to make available information about regions in different ways|Venturini|7|5|4|1|0|0|
|I want a way to handle power-ups|define a power-up manager structure|Venturini|3|3|3|2|1|0|
||make possible to buy power-ups|Venturini|5|5|5|5|3|0|
|I want to show information about regions|model a way for show the infection situation in every region|Venturini|5|5|2|1|0|0|

Sprint 3: 8-14 July
||||||||||
|------|-------|------|------|------|------|------|------|------|
|Product Backlog Item |Sprint Task |Volunteer | Initial Estimate of Effort | 1 | 2 | 3 | 4 | 5 |
|I want a Power-ups' menu|Model the structure for visualize and buy power-ups|Di Girolamo, Venturini|8|4|4|2|2|0|
|I want to improve the infection logic|Improve external infection logic|Di Girolamo|7|7|7|3|1|0|
||Complete the internal infection logic|Di Girolamo|3|3|0|0|0|0|
|I want to interact with the Power-up menu|I want a way to show the power-up hierarchy|Di Girolamo|5|4|2|2|1|1|
||I want to know wich power-ups are purchasable|Di Girolamo|4|4|4|4|2|2|
|I want to add functionalities to the power-ups' menu|I want to model a way to show power-ups' details|Venturini|6|5|4|2|1|0|
||I want to know the virus characteristics during the game|Venturini|6|3|3|3|0|0|
||I want to make power-ups purchasable|Venturini|7|7|5|5|2|0|
|I want to know the general infection situation|i want to know the global infected amount progression|Venturini|2|2|2|2|2|0|
|I want a system that handle DNA-points|I want a way to spawn the DNA-points on the map|Malucelli|7|5|2|2|2|2|
||I want a way to make DNA-points collectables|Malucelli|4|2|2|2|2|2|
||I want a way to handle DNA-points usage|Malucelli|7|4|2|2|2|0|
|I want to know details about a single region|I want a way to show information about a single region|Malucelli|5|5|5|5|5|0|

Sprint 4: 15-24 July
||||||||||
|------|-------|------|------|------|------|------|------|------|
|Product Backlog Item |Sprint Task |Volunteer | Initial Estimate of Effort | 1 | 2 | 3 | 4 | 5 |
|I want to complete the power up grid|I want to change the bottom style depending on bonus' state|Di Girolamo|7|2|2||||
|I want to start the game|I want to create a start menu|Di Girolamo|8|7|2||||
|I want to have a balanced game|I want to balance the power ups' effect|Di Girolamo|5|5|3||||
|I want to collect dna points|I want to show dna points' buttons|Malucelli|7|3|0|0|0|0|
||I want to collect showed dna points|Malucelli|4|4|4||||
|I want to know port and airport's routes|I want to visualize port and airport on the map|Malucelli|3|3|0|0|0|0|
|I want to add vaccine in the game|I want to define a vaccine logic|Di Girolamo, Malucelli, Venturini|7|4|2|2|2||
||I want to integrate the logic in the game|Venturini|8|4|3|2|0|0|
||I want to visualize the vaccine progression|Venturini|3|2|0|0|0|0|
|I want to win or lose the game|I want to define the end game logic|Venturini|5|5|3|1|0|0|

