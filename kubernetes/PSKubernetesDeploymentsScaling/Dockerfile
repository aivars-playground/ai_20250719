# docker-compose.yaml:
# Use Python 3.9
FROM python:3.9

# Set the working directory
WORKDIR /app

# Copy files and install dependencies
COPY app.py /app/app.py
RUN pip install flask

# Expose port 5000 and run the app
CMD ["python", "/app/app.py"]
