# Use a base image with Java installed
FROM openjdk:11

# Set the working directory inside the container
WORKDIR /app

# Copy the Java file to the container
COPY *.java /app

# Compile the Java file
RUN javac *.java

# Set the entrypoint command to run the Java program
CMD ["java", "Students_stat"]
