while True:
  num1 = raw_input("Digite o primeiro numero: ")
  operacao = raw_input("Digite a operacao: ")
  num2 = raw_input("Digite o segundo numero: ")

  

  resultado = None
  bateriamax =  10

  if num1 == "carregar":
    bateria = bateriamax


  

  elif operacao == "+":
      resultado = float(num1) + float(num2)

  elif operacao == "-":
      resultado = float(num1) - float(num2)

  elif operacao == "*":
      resultado = float(num1) * float(num2)

  elif operacao == "/":
      resultado = float(num1) / float(num2)
  
  

  else:
      print("Operacao nao existente!")


  if resultado:
    print("Resultado: {0}".format(resultado), "bateria:", bateriamax-1)
    
  
    break

