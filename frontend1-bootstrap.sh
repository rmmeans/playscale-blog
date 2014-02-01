cp /vagrant/FrontEndServer/target/universal/FrontEndServer-1.0-SNAPSHOT.zip /home/vagrant/
su - vagrant -c "unzip /home/vagrant/FrontEndServer-1.0-SNAPSHOT.zip" 
cp /vagrant/frontend1-control.sh /home/vagrant/
chmod +x frontend1-control.sh
/home/vagrant/frontend1-control.sh start