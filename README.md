# Weather

Hi! A couple of notes...

You'll need to get a key for https://www.weatherapi.com and add it to the local.properties file 
that exists in the root of the project, as follows:

```weather_api_key="your-key-here"```

Don't forget the quotes.

Let me know if you run into any problems with the gradle/idea files... I didn't set up the gitignore
until later than I should have, so there's a pile of files in there. In case it causes problems, 
you should be able to delete any of those directories and/or `.gradlew clean`.