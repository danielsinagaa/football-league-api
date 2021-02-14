# Football League api

This is an api for making a backend of a football league. This api includes team statistics which include the number of wins, losses and points, match statistics and also, player statistics from the number of goals, assists, red cards and yellow cards received.

## Allowed HTTPs requests:
```PUT```     : To create resource 

```POST```    : Update resource

```GET```     : Get a resource or list of resources

```DEL```  : To delete resource

## Tools

```1``` IDE like Intellij or VSCode

```2``` POSTMAN or any application for sending http request and JSON

```3``` Database Management System MySQL

```4``` Web browser that support swagger for api documentation

## Setup

1. Create a database in mysql

2. Match the database name in ```spring: datasource: driver-class-name:``` in the ```resources``` on ```application.properties```

3. Run the app

## API Swagger Documentation

go to ```/swagger-ui-custom.html``` for api documentation

## Extra Info

```-``` When adding a player to api, the playerstat will be automatically created, and when adding the resultstat, 
the playerstat will also automatically change based on the added statistics.

The playerstat contains information about goals, assists, red cards and yellow cards that players get, 
when first created, all of them are 0. And when adding the resultstat containing players, goals, assists, yellow cards and red cards, the playerstat app will change. following these changes

```add new player:```

```JSON
{
    "name": "Timo Werner",
    "number": 11,
    "position": "40281911779fd31901779ff676780005",
    "team": "40281911779a6f6501779a7073fa0000"
}
```

result: 
```JSON
{
    "code": 200,
    "message": "success",
    "data": {
        "list": [
            {
                "id": "4028191177a028980177a02b95de0000",
                "name": "Timo Werner",
                "number": 11,
                "playerPosition": {
                    "id": "40281911779fd31901779ff676780005",
                    "name": "forward"
                },
                "team": {
                    "id": "40281911779a6f6501779a7073fa0000",
                    "name": "Chelsea FC"
                }
            }
        ],
        "page": 0,
        "size": 50,
        "total": 1
    },
    "timestamp": "2021-02-14T17:53:03.3880455"
}
```
the player statistic will be 0 to all stat
playerstat:

```JSON
{
    "code": 200,
    "message": "success",
    "data": {
        "list": [
            {
                "id": "4028191177a028980177a02b961a0001",
                "player": {
                    "id": "4028191177a028980177a02b95de0000",
                    "name": "Timo Werner",
                    "number": 11,
                    "playerPosition": {
                        "id": "40281911779fd31901779ff676780005",
                        "name": "forward"
                    },
                    "team": {
                        "id": "40281911779a6f6501779a7073fa0000",
                        "name": "Chelsea FC"
                    }
                },
                "assist": 0,
                "goal": 0,
                "redCard": 0,
                "yellowCard": 0
            }
        ],
        "page": 0,
        "size": 50,
        "total": 1
    },
    "timestamp": "2021-02-14T17:54:29.3328711"
}
```

and then we ```POST``` the result statistic by this:

```JSON
{
    "player": "4028191177a028980177a02b95de0000",
    "result": "40281911779a6f6501779a7a09810010",
    "goal": 1,
    "assist": 1,
    "yellowCard": 1,
    "redCard": 0
}
```
and the player statistic will be like this:

```JSON
{
    "code": 200,
    "message": "success",
    "data": {
        "list": [
            {
                "id": "4028191177a028980177a02b961a0001",
                "player": {
                    "id": "4028191177a028980177a02b95de0000",
                    "name": "Timo Werner",
                    "number": 11,
                    "playerPosition": {
                        "id": "40281911779fd31901779ff676780005",
                        "name": "forward"
                    },
                    "team": {
                        "id": "40281911779a6f6501779a7073fa0000",
                        "name": "Chelsea FC"
                    }
                },
                "assist": 1,
                "goal": 1,
                "redCard": 0,
                "yellowCard": 1
            }
        ],
        "page": 0,
        "size": 50,
        "total": 1
    },
    "timestamp": "2021-02-14T18:16:52.3784834"
}
```

```-``` This also applies when adding a new api team, teamstat will be automatically created, and when adding a result, 
the teamstat will also change according to the added results.

first, we add the match, that include home team, away team, fixture and referee

home team: ```team id```

away team: ```team id```

fixture: ```fixture id```

referee: ```referee id```

```POST``` match:
```JSON
{
    "home":"40281911779a6f6501779a7073fa0000",
    "away":"40281911779a6f6501779a708b5b0002",
    "fixture":"40281911779a6f6501779a711f180008",
    "referee":"40281911779a6f6501779a72d38a000c"
}
```

result:

```JSON
{
    "code": 200,
    "message": "success",
    "data": {
        "id": "4028191177a028980177a02fe4920002",
        "home": {
            "id": "40281911779a6f6501779a7073fa0000",
            "name": "Chelsea FC"
        },
        "away": {
            "id": "40281911779a6f6501779a708b5b0002",
            "name": "Liverpool FC"
        },
        "fixture": {
            "id": "40281911779a6f6501779a711f180008",
            "week": 1
        },
        "referee": {
            "id": "40281911779a6f6501779a72d38a000c",
            "name": "Oliver"
        }
    },
    "timestamp": "2021-02-14T17:57:09.2778473"
}
```

the team statistic before we ```POST``` the result API:

```JSON
{
    "code": 200,
    "message": "success",
    "data": {
        "list": [
            {
                "id": "40281911779a6f6501779a70745d0001",
                "team": {
                    "id": "40281911779a6f6501779a7073fa0000",
                    "name": "Chelsea FC"
                },
                "win": 0,
                "lose": 0,
                "draw": 0,
                "points": 0
            },
            {
                "id": "40281911779a6f6501779a70cde40005",
                "team": {
                    "id": "40281911779a6f6501779a70cdde0004",
                    "name": "Arsenal FC"
                },
                "win": 0,
                "lose": 0,
                "draw": 0,
                "points": 0
            },
            {
                "id": "40281911779a6f6501779a708b600003",
                "team": {
                    "id": "40281911779a6f6501779a708b5b0002",
                    "name": "Liverpool FC"
                },
                "win": 0,
                "lose": 0,
                "draw": 0,
                "points": 0
            },
            {
                "id": "40281911779a6f6501779a70f1750007",
                "team": {
                    "id": "40281911779a6f6501779a70f16d0006",
                    "name": "Manchester UTD"
                },
                "win": 0,
                "lose": 0,
                "draw": 0,
                "points": 0
            }
        ],
        "page": 0,
        "size": 50,
        "total": 4
    },
    "timestamp": "2021-02-14T18:02:36.5099953"
}
```

and then we ```POST`` the result api:

```JSON
{
    "player": "4028191177a028980177a02b95de0000",
    "result": "40281911779a6f6501779a7a09810010",
    "goal": 1,
    "assist": 1,
    "yellowCard": 1,
    "redCard": 0
}
```

```JSON
{
    "matchs":"4028191177a028980177a02fe4920002",
    "homeGoal": 2,
    "awayGoal": 1
}
```

after that the teamstats will be auto changed by the result
```team statistic``` after ```POST``` a result:

```JSON
{
    "code": 200,
    "message": "success",
    "data": {
        "list": [
            {
                "id": "40281911779a6f6501779a70745d0001",
                "team": {
                    "id": "40281911779a6f6501779a7073fa0000",
                    "name": "Chelsea FC"
                },
                "win": 0,
                "lose": 1,
                "draw": 0,
                "points": 0
            },
            {
                "id": "40281911779a6f6501779a70cde40005",
                "team": {
                    "id": "40281911779a6f6501779a70cdde0004",
                    "name": "Arsenal FC"
                },
                "win": 0,
                "lose": 0,
                "draw": 0,
                "points": 0
            },
            {
                "id": "40281911779a6f6501779a708b600003",
                "team": {
                    "id": "40281911779a6f6501779a708b5b0002",
                    "name": "Liverpool FC"
                },
                "win": 1,
                "lose": 0,
                "draw": 0,
                "points": 3
            },
            {
                "id": "40281911779a6f6501779a70f1750007",
                "team": {
                    "id": "40281911779a6f6501779a70f16d0006",
                    "name": "Manchester UTD"
                },
                "win": 0,
                "lose": 0,
                "draw": 0,
                "points": 0
            }
        ],
        "page": 0,
        "size": 50,
        "total": 
    },
    "timestamp": "2021-02-14T18:07:21.5609138"
}
```

the point system follows the scoring method commonly used in the football league system, namely:

win +3

draw +1

lose 0
