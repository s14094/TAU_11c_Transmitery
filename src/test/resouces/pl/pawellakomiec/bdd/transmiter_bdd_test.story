Given a transmiter
When set arguments like name: audi, model: a4, code: 4466
When add it to arraylist
Then adding should return audi for Transmiter object with code 4466

Given a transmiter
When set arguments like name: bmw, model: 7, code: 3
When add it to arraylist
Then adding should return bmw for Transmiter object with code 3
When set new name Volvo for transmiter with code 3
Then check if data has been updated for transmiter with code 3 and new name Volvo