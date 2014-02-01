apt-get -y install nginx
cp /vagrant/nginx.conf /etc/nginx/nginx.conf
sudo service nginx start
update-rc.d nginx defaults

