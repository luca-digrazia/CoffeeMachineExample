FROM python:3.13-slim

ENV PYTHONDONTWRITEBYTECODE=1 \
    PYTHONUNBUFFERED=1 \
    PIP_NO_CACHE_DIR=1 \
    PIP_DISABLE_PIP_VERSION_CHECK=1

WORKDIR /app

# Install pinned dependencies first to leverage layer caching
COPY requirements.txt requirements-lock.txt ./
RUN pip install -r requirements-lock.txt

COPY . .

# Default: run the test suite. Override, e.g.:
#   docker run --rm coffee-machine mutmut run
#   docker run --rm coffee-machine coverage run -m unittest && coverage report
CMD ["python", "-m", "unittest", "-v", "test_coffee_machine.py"]
