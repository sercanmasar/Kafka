# Kafka

Steps: 

- Download kafka  https://kafka.apache.org/downloads -> as Binary downloads:
Scala 2.12  - kafka_2.12-3.4.0.tgz (asc, sha512)

- Create file kafka-server and copy kafka_2.12-3.4.0 in it

- Open Terminal(Zookeeper Server)

1 - #!/usr/bin/env bash
cd ~/kafka-server

2 - kafka/bin/zookeeper-server-start.sh \
   kafka/config/zookeeper.properties

- Open Second Terminal (Kafka Server)

1 - #!/usr/bin/env bash
cd ~/kafka-server

2- kafka/bin/kafka-server-start.sh \
    kafka/config/server.properties
    
- Open Third Terminal (Topics)

1- cd ~/kafka-training

2- kafka/bin/kafka-topics.sh --create \
  --zookeeper localhost:2181 \
  --replication-factor 1 --partitions 13 \
  --topic my-topic


