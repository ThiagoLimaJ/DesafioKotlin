import java.time.LocalDateTime

class DigitalHouseManager(var listaDeAlunos: MutableList<Aluno>,
                          var listaDeProfessores: MutableList<Professor>,
                          var listaDeCursos: MutableList<Curso>,
                          var listaDeMatriculas: MutableList<Matricula>)
{
    fun registrarCurso(nome: String, codigoCurso: Int,quantidadeMaximaDeAlunos: Int){
        var curso = Curso(nome,codigoCurso,quantidadeMaximaDeAlunos)
        listaDeCursos.add(curso)
    }

    fun excluirCurso(codigoCurso: Int){
        for(curso in listaDeCursos){
            if(curso.codigoCurso == codigoCurso){
                listaDeCursos.remove(curso)
            }
        }
    }

    fun registrarProfessorAdjunto(nome: String, sobrenome: String,codigoProfessor: Int, quantidadeDeHoras: Int){
        var professorAdjunto = ProfessorAdjunto(nome,sobrenome,codigoProfessor,0,quantidadeDeHoras)
        listaDeProfessores.add(professorAdjunto)
    }

    fun registrarProfessorTitular(nome: String, sobrenome: String ,codigoProfessor: Int, especialidade: String){
        var professorTitular = ProfessorTitular(nome,sobrenome,0,codigoProfessor,especialidade)
        listaDeProfessores.add(professorTitular)
    }

    fun excluirProfessor(codigoProfessor: Int){
        for(professor in listaDeProfessores){
            if(professor.codigoProfessor == codigoProfessor){
                listaDeProfessores.remove(professor)
            }
        }
    }

    fun matricularAluno(nome: String , sobrenome: String , codigoAluno: Int){
        var aluno = Aluno(nome,sobrenome,codigoAluno)
        listaDeAlunos.add(aluno)
    }

    fun matricularAluno(codigoAluno: Int,  codigoCurso: Int){
        var alunoM: Aluno? = null
        var cursoM: Curso? = null

        for (aluno in listaDeAlunos){
            if(aluno.codigoAluno == codigoAluno){
                alunoM = aluno
            }
        }
        for (curso in listaDeCursos){
            if(curso.codigoCurso == codigoCurso){
                cursoM = curso
            }
        }
        if (alunoM == null){
            throw Exception("Aluno inexistente.")
        }
        if (cursoM == null){
            throw Exception("Curso inexistente.")
        }

        if (cursoM.quantidadeMaximaAlunos > cursoM.listaAlunosMatriculados.size){
            var lista = Matricula(alunoM,cursoM, LocalDateTime.now())
            listaDeMatriculas.add(lista)
            println("Aluno matriculado.")
        } else{
            println("Não foi possível realizar a matrícula, não existe vaga disponível.")
        }
    }

    fun alocarProfessores(codigoCurso: Int, codigoProfessorTitular: Int,codigoProfessorAdjunto: Int){
        var professorT: ProfessorTitular? = null
        var professorA: ProfessorAdjunto? = null
        var cursoA: Curso? = null

        for (professor in listaDeProfessores){
            if (professor.codigoProfessor == codigoProfessorTitular){
                professorT = professor as ProfessorTitular
            } else if (professor.codigoProfessor == codigoProfessorAdjunto){
                professorA = professor as ProfessorAdjunto
            }
        }
        for (curso in listaDeCursos){
            if (curso.codigoCurso == codigoCurso){
                cursoA = curso
            }
        }
        if (professorT == null){
            throw Exception("Professor titular inexistente.")
        }
        if (professorA == null){
            throw Exception("Professor adjunto inexistente.")
        }
        if (cursoA == null){
            throw Exception("Curso inexistente.")
        }
        cursoA.professorTitular = professorT
        cursoA.professorAdjunto = professorA
    }

}