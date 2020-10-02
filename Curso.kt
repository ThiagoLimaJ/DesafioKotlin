class Curso(val nomeCurso: String, val codigoCurso: Int, var quantidadeMaximaAlunos: Int)
{
    var professorAdjunto: ProfessorAdjunto? = null
    var professorTitular: ProfessorTitular? = null
    var listaAlunosMatriculados: MutableList<Aluno> = mutableListOf()

    fun adicionarUmAluno(umAluno: Aluno): Boolean{
        if (quantidadeMaximaAlunos > listaAlunosMatriculados.size){
            listaAlunosMatriculados.add(umAluno)
            return true
        } else{
            return false
        }
    }

    fun excluirAluno(umAluno: Aluno){
        listaAlunosMatriculados.remove(umAluno)
    }
}