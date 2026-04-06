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

resource "aws_instance" "servidor_web" {
  # Indicamos que queremos 3 instancias
  count         = 3

  ami           = "ami-0c7217cdde317cfec"
  instance_type = "t3.micro"
  key_name      = aws_key_pair.mi_llave.key_name
  vpc_security_group_ids = [aws_security_group.allow_web.id]

  user_data = <<-EOF
              #!/bin/bash
              apt-get update -y
              apt-get install -y nginx
              systemctl start nginx
              systemctl enable nginx
              echo "<h1>Servidor Renfe API - Instancia ${count.index}</h1>" > /var/www/html/index.html
              EOF

  tags = {
    # Usamos count.index para diferenciarlas: Servidor-0, Servidor-1, etc.
    Name = "Servidor-Nginx-Ubuntu-${count.index}"
  }
}

# Modificamos el output para que muestre la lista de IPs de todas las instancias
output "instancia_ips" {
  value = aws_instance.servidor_web[*].public_ip
}