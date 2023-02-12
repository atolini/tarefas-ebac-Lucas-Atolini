package org.example;

import org.example.dao.ClienteMapDAO;
import org.example.dao.IClienteDAO;
import org.example.domain.Cliente;

import javax.swing.*;
import java.util.List;

public class Main {
    private static IClienteDAO iClienteDAO;

    public static void main(String args[]) {
        iClienteDAO = new ClienteMapDAO();
        menu("0");
    }

    private static void menu(String opcao) {
        if (opcao.equals("0")) {
            opcao = JOptionPane.showInputDialog(null,
                "Digite 1 para cadastro, 2 para consultar, 3 para exclusão, 4 para alteração ou 5 para sair",
                "Cadastro", JOptionPane.INFORMATION_MESSAGE);
        }

        switch (opcao) {
            case "1" -> {
                String dados = JOptionPane.showInputDialog(null,
                        "Digite os dados separados por vírgula, conforme exemplo: Nome, CPF, Telefone, Endereço, " +
                                "Número, Cidade e Estado", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                cadastrar(dados);
                break;
            }
            case "2" -> {
                String dados = JOptionPane.showInputDialog(null,
                        "Digite o cpf:", "Consulta", JOptionPane.INFORMATION_MESSAGE);
                consultar(dados);
                break;
            }
            case "3" -> {
                String dados = JOptionPane.showInputDialog(null,
                        "Digite o cpf:", "Exclusão", JOptionPane.INFORMATION_MESSAGE);
                excluir(dados);
                break;
            }
            case "4" -> {
                String dados = JOptionPane.showInputDialog(null,
                        "Digite os dados separados por vírgula, conforme exemplo: Nome, CPF, Telefone, Endereço, " +
                                "Número, Cidade e Estado", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                alterar(dados);
                break;
            }
            case "5" -> {
                sair();
                break;
            }
            default -> {
                sair();
            }
        }
    }

    private static void alterar(String dados) {
        List<String> input = List.of(dados.split(","));
        if (input.size() == 7) {
            String cpf = input.get(1);
            Boolean isNumber = cpf.transform((d) -> d.trim().matches("[0-9]*") && d.trim().length() == 11);

            if (isNumber) {
                Cliente cliente = new Cliente(
                        input.get(0),
                        input.get(1),
                        input.get(2),
                        input.get(3),
                        input.get(4),
                        input.get(5),
                        input.get(6));
                Boolean isCadastrado = iClienteDAO.cadastrar(cliente);
                if (isCadastrado) {
                    JOptionPane.showMessageDialog(null, "Cliente cadastrado!", "Sucesso!",
                            JOptionPane.INFORMATION_MESSAGE);
                    menu("4");
                } else {
                    JOptionPane.showMessageDialog(null, "Cliente já cadastrado!", "Erro!",
                            JOptionPane.INFORMATION_MESSAGE);
                    menu("4");
                }
            } else {
                JOptionPane.showMessageDialog(null, "CPF inválido!",
                        "Erro", JOptionPane.INFORMATION_MESSAGE);
                menu("4");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Informações insuficientes! Tente novamente.", "Erro!",
                    JOptionPane.INFORMATION_MESSAGE);
            menu("4");
        }
    }

    private static void excluir(String dados) {
        Boolean isNumber = dados.transform((d) -> d.trim().matches("[0-9]*") && d.trim().length() == 11);
        if (isNumber) {
            iClienteDAO.excluir(Long.parseLong(dados));
            JOptionPane.showMessageDialog(null, "Cliente excluido!",
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "CPF inválido!",
                    "Erro", JOptionPane.INFORMATION_MESSAGE);
            menu("3");
        }
    }

    private static void consultar(String dados) {
        Boolean isNumber = dados.transform((d) -> d.trim().matches("[0-9]*") && d.trim().length() == 11);
        if (isNumber) {
            Cliente c = iClienteDAO.consultar(Long.parseLong(dados));
            if (c != null) {
                JOptionPane.showMessageDialog(null, "Cliente encontrado! " + c.toString(),
                        "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado!",
                        "Erro", JOptionPane.INFORMATION_MESSAGE);
                menu("0");
            }
        } else {
            JOptionPane.showMessageDialog(null, "CPF inválido!",
                    "Erro", JOptionPane.INFORMATION_MESSAGE);
            menu("2");
        }
    }

    private static void cadastrar(String dados) {
        List<String> input = List.of(dados.split(","));
        if (input.size() == 7) {
            String cpf = input.get(1);
            Boolean isNumber = cpf.transform((d) -> d.trim().matches("[0-9]*") && d.trim().length() == 11);

            if (isNumber) {
                Cliente cliente = new Cliente(
                        input.get(0),
                        input.get(1),
                        input.get(2),
                        input.get(3),
                        input.get(4),
                        input.get(5),
                        input.get(6));
                Boolean isCadastrado = iClienteDAO.cadastrar(cliente);
                if (isCadastrado) {
                    JOptionPane.showMessageDialog(null, "Cliente cadastrado!", "Sucesso!",
                            JOptionPane.INFORMATION_MESSAGE);
                    menu("0");
                } else {
                    JOptionPane.showMessageDialog(null, "Cliente já cadastrado!", "Erro!",
                            JOptionPane.INFORMATION_MESSAGE);
                    menu("0");
                }
            } else {
                JOptionPane.showMessageDialog(null, "CPF inválido!",
                        "Erro", JOptionPane.INFORMATION_MESSAGE);
                menu("4");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Informações insuficientes! Tente novamente.", "Erro!",
                    JOptionPane.INFORMATION_MESSAGE);
            menu("1");
        }
    }

    public static void sair() {
        System.exit(0);
    }
}