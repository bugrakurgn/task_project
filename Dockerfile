FROM ubuntu:latest
LABEL authors="LocalAdm"

ENTRYPOINT ["top", "-b"]