function [ C,EK ] = kmeans( S,K )
% S = input data
% K = number of clusters
% initial cluster centers
RAND = randperm(length(S));
RAND = RAND(1:K);
CC = S(RAND,:);
ITER = 0;
% used to store cluster index
I = zeros(length(S),1);
% error calculations
LAST_EK = bitmax;
while(true)
    EK = 0;
    ITER = ITER + 1;
    %compute distances & find nearest center
    DIF = kron(S,ones(K,1)) - repmat(CC,length(S),1);
    % only diagonal elemnts are interesting
    D = reshape(diag(DIF * DIF'),K,size(S,1))';

    [MIN,I] = min(D,[],2);
    %calculate new cluster centers
    %and error
    for j = [1:K]    
        XI = S(I==j,:);
        XI_LEN = size(XI,1);
        CC(j,:) = sum(XI) / XI_LEN;
        % ej
        EJ_DIF = XI - repmat(CC(j,:),XI_LEN,1);
        EJ = sum(diag(EJ_DIF * EJ_DIF'));
        EK = EK + EJ;  
    end
    
    if(LAST_EK-EK <= 0)
        break;
    else
        LAST_EK=EK;
    end
    
end

fprintf('iterations: %d\n',ITER);
C=I;
end



