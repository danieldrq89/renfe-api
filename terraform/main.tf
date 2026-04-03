provider "aws" {
  region = "us-east-1"
}

# 2. Security Group: AHORA CON PUERTO 80 (HTTP)
resource "aws_security_group" "allow_web" {
  name        = "allow_web_traffic"
  description = "Permitir SSH y HTTP"

  ingress {
    description = "SSH"
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    description = "HTTP"
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

resource "aws_key_pair" "mi_llave" {
  key_name   = "llave-renfe-final"
  public_key = file("${path.module}/.ssh/llave-renfe.pub")
}

# 4. Instancia EC2 con Nginx
resource "aws_instance" "servidor_web" {
  ami           = "ami-0c7217cdde317cfec" # Ubuntu
  instance_type = "t3.micro"
  key_name      = aws_key_pair.mi_llave.key_name
  vpc_security_group_ids = [aws_security_group.allow_web.id]

  # SCRIPT DE INSTALACIÓN AUTOMÁTICA
  user_data = <<-EOF
              #!/bin/bash
              apt-get update -y
              apt-get install -y nginx
              systemctl start nginx
              systemctl enable nginx
              echo "<h1>Servidor Renfe API - Nginx funcionando</h1>" > /var/www/html/index.html
              EOF

  tags = {
    Name = "Servidor-Nginx-Ubuntu"
  }
}

output "instancia_ip" {
  value = aws_instance.servidor_web.public_ip
}