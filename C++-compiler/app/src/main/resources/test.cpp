int main() {
    string option = "n";
    int _pedido, _precoFinal = 0, idade = 0, quantidadeMaxima = 10, quantidade = 0, position = 0, precoCerveja = 5, precoSuco = 2, precoRefri = 8;
    std::string compras[quantidadeMaxima];
    int quantidades[quantidadeMaxima];
    int precos[quantidadeMaxima];
    string mensagem = "Informe o ano do seu nascimento: ";

    cout << "Bem vindo a minha loja de bebidas!";
    cin >> idade;

    while (option != "s" || position <= compras.length) {
        quantidade = 0;
        idade = 0;

        cout << "1 - Cerveja";
        cout << "2 - Suco";
        cout << "3 - Refrigerante";
        cout << "Informe a seu _pedido: ";
        cin >> _pedido;
        cout << "Informe sua idade";
        cin >> idade;

        if (_pedido == 1 && idade >= 18) {
            cout << "Informe a quantidade: ";
            cin >> quantidade;
            cout << "Você pediu " << quantidade << " cerveja(s)"
            compras[position] = "Cerveja";
            quantidades[position] = quantidade;
            precos[position] = quantidade * precoCerveja;
            position = position + 1;
        } else if (_pedido == 2) {
            cout << "Informe a quantidade: ";
            cin >> quantidade;
            cout << "Você pediu " << quantidade << " suco(s)";
            compras[position] = "Suco";
            quantidades[position] = quantidade;
            precos[position] = quantidade * precoSuco;
            position = position + 1;
        } else if (_pedido == 3) {
            cout << "Informe a quantidade: ";
            cin >> quantidade;
            cout << "Você pediu " << quantidade << " refrigerante(s)";
            compras[position] = "Refrigerante";
            quantidades[position] = quantidade;
            precos[position] = quantidade * precoRefri;
            position = position + 1;
        } else {
            cout << "Opção inválida.";
        }

        if(position >= quantidadeMaxima) {
            cout << "QUantide máxima de _pedidos alcançada";
        } else {
            cout << "Deseja continuar essa compra";
            cin >> option;
        }
    }

    for (int i = 0; i < precos.length; i++) {
        cout << "Tá repetindo " << i;
    }

    return 0;
}