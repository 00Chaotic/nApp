# nApp
This Android app aims to facilitate fair access to shared community washing machine facilities for Venezuelan communities supporting a reusable nappy washing social enterprise.

# Getting Started
Below is some basic information provided to get started with this repository.

## Secrets file
Android does not have `.env` functionality, so a `secrets.properties` file is used as a substitue, loading variables into the `BuildConfig` object, which can then be accessed by the app.

When cloning this repository for the first time, make a copy of the `secrets-example.properties` file and rename it to `secrets.properties`. This can be done by copy-pasting the file, or it can be done through the terminal (assuming you are at the project root), as shown below.

Linux/macOS:
```
$ cp secrets-example.properties secrets.properties
```
Windows:
```
> copy secrets-example.properties secrets.properties
```
