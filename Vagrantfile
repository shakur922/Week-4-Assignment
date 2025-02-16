Vagrant.configure("2") do |config|
    # Use the official Ubuntu 20.04 LTS box
    config.vm.box = "ubuntu/focal64"
  
    # Provisioning with shell script
    config.vm.provision "shell", inline: <<-SHELL
      # Update package list and install PostgreSQL
      apt-get update
      apt-get install -y postgresql postgresql-contrib
  
      # Switch to the postgres user
      sudo -u postgres psql -c "CREATE DATABASE school;"

      # Grant privileges on all tables in the public schema
      # Optionally, you can create a user and grant privileges
      sudo -u postgres psql -c "CREATE USER school_user WITH PASSWORD 'password';"
      sudo -u postgres psql -d school -c "GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO school_user;"
      sudo -u postgres psql -d school -c "ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL PRIVILEGES ON TABLES TO school_user;"
      sudo -u postgres psql -d school -c "GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO school_user;"
      sudo -u postgres psql -d school -c "ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL PRIVILEGES ON SEQUENCES TO school_user;"


      sudo -u postgres psql -d school -c "CREATE TABLE students (
        id SERIAL PRIMARY KEY,
        name VARCHAR(100) NOT NULL,
        age INT NOT NULL,
        email VARCHAR(100) UNIQUE NOT NULL
      );"

      # insert a few test students
      sudo -u postgres psql -d school -c "insert into students
      (name, age, email) 
      values 
      ('John Doe', 20, 'jdoe@cmcc.edu'),
      ('Jane Doe',21,'jadoe@cmcc.edu'),
      ('John Smith', 22, 'jsmith@cmcc.edu');"
      


      sudo -u postgres psql -d school -c "CREATE TABLE classes (
        class_code text primary key,
        class_name text
      );"

      sudo -u postgres psql -d school -c "insert into classes
      (class_code, class_name)
      values
      ('CPT-101', 'Intro to Computer Science'),
      ('CPT-127','Intro to Python'),
      ('CPT-245','Inro to Java');"

      # Allow PostgreSQL to accept connections from the host
      echo "listen_addresses = '*'" | sudo tee -a /etc/postgresql/12/main/postgresql.conf
      echo "host    all             all             0.0.0.0/0               md5" | sudo tee -a /etc/postgresql/12/main/pg_hba.conf
      # Restart PostgreSQL to apply changes
      sudo systemctl restart postgresql
    SHELL
  
    # Forward PostgreSQL port
    config.vm.network "forwarded_port", guest: 5432, host: 5001
  end
  