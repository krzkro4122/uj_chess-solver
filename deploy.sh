#!/bin/bash

tar -cvzf kkrol.tgz *
scp -i ~/.ssh/id_rsa kkrol.tgz krol_1197262@spk-ssh.if.uj.edu.pl:/home/krol_1197262
ssh -i ~/.ssh/id_rsa krol_1197262@spk-ssh.if.uj.edu.pl "java -jar /scratch/uforamus/ChessTestClient/client.jar 172.30.24.15 kkrol.tgz"
