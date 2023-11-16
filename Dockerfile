# Use a base image with Java installed
FROM openjdk:17

# Set the working directory in the container
WORKDIR /app

# Copy the Java source code into the container
COPY . /app

# Compile the Java code
RUN javac fourInLine/PlayGame.java

# Define the command to run the application
CMD ["java", "fourInLine.PlayGame"]
