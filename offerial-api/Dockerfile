FROM frolvlad/alpine-oraclejdk8:latest

WORKDIR /

ENV DIRPATH /com/worldpay

RUN mkdir -p $DIRPATH

ADD init.sh $DIRPATH/init.sh
ADD offerial-config.yml $DIRPATH/offerial-config.yml
ADD build/offerial.jar $DIRPATH/offerial.jar

EXPOSE 9999

ENTRYPOINT ["/com/worldpay/init.sh"]
