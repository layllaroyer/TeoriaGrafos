import numpy as np
import matplotlib.pyplot as plt
from igraph import *


class Grafo:
    def __init__(self, quantVertices):
        self.vertices = []
        self.quantVertices = quantVertices
        self.grafo = np.zeros((self.quantVertices, self.quantVertices))

    def addAresta(self, u, v, distancia):
        self.grafo[u, v] = distancia

    def addVertice(self, aeroporto):
        self.vertices.append(aeroporto)

    def mostraMatriz(self):
        for i in range(self.quantVertices):
            print(self.grafo[i])

#g2 = Graph()
#g2.add_vertex('Aeroporto Internacional de Guarulhos')
#g2.add_vertex('Aeroporto Internacional de Las Vegas')
#plot(g2)

f = open('arquivo.txt', 'r')
quantidadeAeroportos = int(f.readline())
print(quantidadeAeroportos)
g = Grafo(quantidadeAeroportos)
count = 0
while count < quantidadeAeroportos:
    aeroporto = f.readline()
    g.addVertice(aeroporto)
    count += 1;
linha = f.readline()

count = 0
while count < quantidadeAeroportos:
    linha = f.readline()
    linha = linha.strip('\n')  
    dados = []
    dados.append(linha.split(' '))
    count2 = 0
    while count2 < quantidadeAeroportos:
        aux = int(dados[0][count2])
        g.addAresta(count, count2, aux)
        count2 += 1;
    count += 1;
f.close
g.mostraMatriz()