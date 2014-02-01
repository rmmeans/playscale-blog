sudo apt-get install -y memcached
sudo service memcached stop
sudo cp /vagrant/memcached.conf /etc/memcached.conf
sudo service memcached start