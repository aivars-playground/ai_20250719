# Flask app:
from flask import Flask
import time

app = Flask(__name__)

# Store the time the pod started (persistent across function calls)
START_TIME = time.time()

@app.route("/")
def home():
    return "Hello, Kubernetes!", 200

@app.route("/health")
def health():
    """Fails for the first 30 seconds, then recovers"""
    elapsed_time = time.time() - START_TIME
    if elapsed_time < 30:  # Simulating failure for 30 seconds
        return "Unhealthy", 500
    return "OK", 200

if __name__ == "__main__":
    time.sleep(5)  # Simulating slow startup
    app.run(host="0.0.0.0", port=5000)
