# toggle-app

##Simple toggle example

###This project show how toggle features works using Togglz lib.
###Instagram api is only as addon.

Toggle url:

http://localhost:8080/toggle-app/togglz/index

To configure state toggle you can edit feature.properties add set featrue state

`togglz.features.NEW FEATURE.enabled=true`


To run you need maven and java.

In command line run
`mvn spring-boot:run`

# Services

## Toggle

http://localhost:8080/toggle-app/togglz/index


## Searchuser post

http://localhost:8080/toggle-app/instagram/searchuser

Example json 

{
	"user": "github", 
	"loginData":{
	"email":"XXXXXXXX",
	"password":"XXXX"
	}
}

## Login post

http://localhost:8080/toggle-app/user/login

Example json 
{
	"email":"XXXXXXXX",
	"password":"XXXX"
}

## Get feature list GET

http://localhost:8080/toggle-app/feature/get

## Get followers list for user with pk get from searchuser POST

http://localhost:8080/toggle-app/instagram/followers

Example json 
{
	"pk": 19318909,
	 "username": "github", <- optional
	 "full_name": "GitHub",  <- optional
	"loginData":{
	"email":"XXXXX",
	"password":"XXXXX"
	}
}

## Follow user POST

http://localhost:8080/toggle-app/instagram/follow

Example json 

{
	"pk": 19318909,
	 "username": "github",  <- optional
	 "full_name": "GitHub",  <- optional
	"loginData":{
	"email":"xXXXX",
	"password":"XXXXX"
	}
}

## Unfollow user POST

http://localhost:8080/toggle-app/instagram/unfollow

Example json

{
	"pk": 19318909,
	 "username": "github",  <- optional
	 "full_name": "GitHub",  <- optional
	"loginData":{
	"email":"XXXXX",
	"password":"XXXXX"
	}
}

## Get feed for a tag POST

http://localhost:8080/toggle-app/instagram/tag

Example json 

{
	"user": "github", <- optional
	 "tag": "github",
	"loginData":{
	"email":"XXXXX",
	"password":"XXXXX"
	}
}

## Perform a like operation for a media (hashtag) POST

http://localhost:8080/toggle-app/instagram/like

Example json 

{
	"pk": "1730440662648923701",
	 "comment": "life",  <- optional
	"loginData":{
	"email":"xxxxxxx",
	"password":"xxxxx"
	}
}

## Add a comment for a media (tag) POST

http://localhost:8080/toggle-app/instagram/comment

Example json 

{
	"pk": "1730440662648923701",
	 "comment": "nice",
	"loginData":{
	"email":"xxxx",
	"password":"xxxxx"
	}
}



